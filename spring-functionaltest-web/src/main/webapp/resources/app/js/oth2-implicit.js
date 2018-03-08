var oauth2Func = (function(exp, $) {
    "use strict";

    var config = {}, DEFAULT_LIFETIME = 10;

    var uuid = function() {
        return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g,
                function(c) {
                    var r = Math.random() * 16 | 0, v = c === "x" ? r
                            : (r & 0x3 | 0x8);
                    return v.toString(16);
                });
    };

    var encodeURL = function(url, params) {
        var res = url;
        var k, i = 0;
        for (k in params) {
            res += (i++ === 0 ? "?" : "&") + encodeURIComponent(k) + "="
                    + encodeURIComponent(params[k]);
        }
        return res;
    };

    var epoch = function() {
        return Math.round(new Date().getTime() / 1000.0);
    };

    var parseFailureJSON = function(providerId, data) {
        var res = data.responseJSON;
        var error = res.error;
        var errorDescription = res.error_description;
        var co = config[providerId];

        if (error === "invalid_token" && errorDescription) {
            var tokens = getTokens(providerId);
            for (var token of tokens) {
                if (errorDescription.indexOf(token["access_token"]) >= 0) {
                    if (errorDescription.indexOf("Invalid access token") < 0) {
                        // clear expired tokens
                        wipeTokens(providerId)

                        // redirect for get access token
                        if (co["redirectUrl"]) {
                            redirect(co["redirectUrl"]);
                            return;
                        }
                    }
                    break;
                }
            }
        }

        if (co["errRedirectUrl"]) {
            var request = {};
            if (error) {
                request["error"] = error;
            }
            if (errorDescription) {
                request["error_description"] = errorDescription;
            }
            redirect(encodeURL(co["errRedirectUrl"], request));
        } else {
            alert("Access Error. cause: " + error + "/" + errorDescription);
        }
    };

    var parseQueryString = function(qs) {
        var e, a = /\+/g, r = /([^&;=]+)=?([^&;]*)/g, d = function(s) {
            return decodeURIComponent(s.replace(a, " "));
        }, q = qs, urlParams = {};

        while (e = r.exec(q)) {
            urlParams[d(e[1])] = d(e[2]);
        }

        return urlParams;
    };

    var saveState = function(state, obj) {
        localStorage.setItem("state-" + state, JSON.stringify(obj));
    };

    var getState = function(state) {
        var obj = JSON.parse(localStorage.getItem("state-" + state));
        localStorage.removeItem("state-" + state);
        return obj;
    };

    var hasScope = function(token, scope) {
        if (!token.scopes)
            return false;
        var i;
        for (i = 0; i < token.scopes.length; i++) {
            if (token.scopes[i] === scope)
                return true;
        }
        return false;
    };

    var filterTokens = function(tokens, scopes) {
        if (!scopes)
            scopes = [];

        var i, j, result = [], now = epoch(), usethis;
        for (i = 0; i < tokens.length; i++) {
            usethis = true;

            if (tokens[i].expires && tokens[i].expires < (now + 1))
                usethis = false;

            for (j = 0; j < scopes.length; j++) {
                if (!hasScope(tokens[i], scopes[j]))
                    usethis = false;
            }

            if (usethis)
                result.push(tokens[i]);
        }
        return result;
    };

    var saveTokens = function(provider, tokens) {
        localStorage.setItem("tokens-" + provider, JSON.stringify(tokens));
    };

    var getTokens = function(provider) {
        var tokens = JSON.parse(localStorage.getItem("tokens-" + provider));
        if (!tokens)
            tokens = [];

        return tokens;
    };

    var wipeTokens = function(provider) {
        localStorage.removeItem("tokens-" + provider);
    };

    var saveToken = function(provider, token) {
        var tokens = getTokens(provider);
        tokens = filterTokens(tokens);
        tokens.push(token);
        saveTokens(provider, tokens);
    };

    var getToken = function(provider, scopes) {
        var tokens = getTokens(provider);
        tokens = filterTokens(tokens, scopes);
        if (tokens.length < 1)
            return null;
        return tokens[0];
    };

    var handleError = function(providerId, cause) {
        if (!config[providerId])
            throw "Could not retrieve config for this provider.";

        var co = config[providerId];
        var errorDetail = cause["error"];
        var errorDescription = cause["error_description"];

        // redirect error page
        if (co["errRedirectUrl"]) {
            var request = {};
            if (errorDetail) {
                request["error"] = errorDetail;
            }
            if (errorDescription) {
                request["error_description"] = errorDescription;
            }
            redirect(encodeURL(co["errRedirectUrl"], request));
        } else {
            alert("Access Error. cause: " + errorDetail + "/"
                    + errorDescription);
        }
    };

    var sendAuthRequest = function(providerId, scopes) {
        if (!config[providerId])
            throw "Could not find configuration for provider " + providerId;
        var co = config[providerId];

        var state = uuid();
        var request = {
            "response_type" : "token"
        };
        request.state = state;

        if (co["redirectUrl"]) {
            request["redirect_uri"] = co["redirectUrl"];
        }
        if (co["clientId"]) {
            request["client_id"] = co["clientId"];
        }
        if (scopes) {
            request["scope"] = scopes.join(" ");
        }

        var authurl = encodeURL(co.authorization, request);

        if (window.location.hash) {
            request["restoreHash"] = window.location.hash;
        }
        request["providerId"] = providerId;
        if (scopes) {
            request["scopes"] = scopes;
        }

        saveState(state, request);
        redirect(authurl);

    };

    var checkForToken = function(providerId) {
        var h = window.location.hash;

        if (h.length < 2)
            return true;

        if (h.indexOf("error") > 0) {
            h = h.substring(1);
            var errorinfo = parseQueryString(h);
            handleError(providerId, errorinfo);
            return false;
        }

        if (h.indexOf("access_token") === -1) {
            return true;
        }
        h = h.substring(1);
        var atoken = parseQueryString(h);

        if (!atoken.state) {
            return true;
        }

        var state = getState(atoken.state);
        if (!state)
            throw "Could not retrieve state";
        if (!state.providerId)
            throw "Could not get providerId from state";
        if (!config[state.providerId])
            throw "Could not retrieve config for this provider.";

        var now = epoch();
        if (atoken["expires_in"]) {
            atoken["expires"] = now + parseInt(atoken["expires_in"]);
        } else {
            atoken["expires"] = now + DEFAULT_LIFETIME;
        }

        if (atoken["scope"]) {
            atoken["scopes"] = atoken["scope"].split(" ");
        } else if (state["scopes"]) {
            atoken["scopes"] = state["scopes"];
        }

        saveToken(state.providerId, atoken);

        if (state.restoreHash) {
            window.location.hash = state.restoreHash;
        } else {
            window.location.hash = "";
        }
        return true;
    };

    var redirect = function(url) {
        window.location = url;
    };

    var initialize = function(c) {
        config = c;
        try {
            var key, providerId;
            for (key in c) {
                providerId = key;
            }
            return checkForToken(providerId);
        } catch (e) {
            console.log("Error when retrieving token from hash: " + e);
            window.location.hash = "";
            return false;
        }
    };

    var clearTokens = function(config) {
        var key;
        for (key in config) {
            wipeTokens(key);
        }
    };

    // function for testOTH21101006
    var delayed = function() {
        var d = new $.Deferred;
        setTimeout(function() {
            d.resolve();
        }, 10000);
        return d.promise();
    };

    var oajax = function(settings, delay) {
        var providerId = settings.providerId;
        var scopes = settings.scopes;
        var token = getToken(providerId, scopes);

        if (!token) {
            sendAuthRequest(providerId, scopes);
            return;
        }

        if (!settings.headers)
            settings.headers = {};
        settings.headers["Authorization"] = "Bearer " + token["access_token"];

        if (delay) {
            // function for testOTH21101006
            return delayed().then(function() {
                return $.ajax(settings)
            });
        } else {
            return $.ajax(settings);
        }
    };

    return {
        parseFailureJSON : function(providerId, data) {
            return parseFailureJSON(providerId, data);
        },
        getTokens : function(provider) {
            return getTokens(provider);
        },
        initialize : function(config) {
            return initialize(config);
        },
        clearTokens : function(config) {
            return clearTokens(config);
        },
        oajax : function(settings, delay) {
            return oajax(settings, delay);
        },
    };

})(window, jQuery);