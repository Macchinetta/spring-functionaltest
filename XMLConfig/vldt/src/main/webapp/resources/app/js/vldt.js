function addressTemplate(number) {
    return `
<fieldset class="address">
    <legend id="Address${number}">Address${number + 1}</legend>
    <div class="form-group">
        <label for="addresses${number}.userName" class="col-sm-2 control-label">UserName</label>
        <div class="col-sm-10">
            <input id="addresses${number}.userName" name="addresses[${number}].userName" placeholder="UserName" class="form-control" type="text" />
        </div>
    </div>
    <div class="form-group">
        <label for="addresses${number}.postCode" class="col-sm-2 control-label">PostCode</label>
        <div class="col-sm-10">
            <input id="addresses${number}.postCode" name="addresses[${number}].postCode" placeholder="PostCode" class="form-control" type="text" />
        </div>
    </div>
    <div class="form-group">
        <label for="addresses${number}.address" class="col-sm-2 control-label">Address</label>
        <div class="col-sm-10">
            <input id="addresses${number}.address" name="addresses[${number}].address" placeholder="Address" class="form-control" type="text" />
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button id="remove${number}" data-action="remove-address" class="btn btn-default">remove</button>
        </div>
    </div>
</fieldset>
<br />
`;
}

class AddressesView {
    constructor() {
        this.addressSize = $("fieldset.address").length;
    }

    addAddress() {
        const newHtml = addressTemplate(this.addressSize);
        const $lastFieldset = $("fieldset.address").last();

        if ($lastFieldset.length > 0) {
            // Insert after the last <br>
            $lastFieldset.next("br").after($(newHtml));
        } else {
            // If no fieldset exists, console a warning
            console.warn("No existing fieldset found");
        }

        this.addressSize++;
    }

    removeAddress($fieldset) {
        $fieldset.next().remove(); // remove <br>
        $fieldset.remove(); // remove <fieldset>
        this.addressSize = $("fieldset.address").length; // Update size after removal

        // Reindex all fieldsets after removal
        this.reindexAddresses();
    }

    reindexAddresses() {
        $("fieldset.address").each(function (index) {
            const $fieldset = $(this);

            // Update legend text and id
            $fieldset
                .find("legend")
                .text(`Address${index + 1}`)
                .attr("id", `Address${index}`);

            // Update id, name, and for attributes for each input field
            // UserName
            $fieldset.find(`input[name^="addresses"][name$=".userName"]`).attr({
                id: `addresses${index}.userName`,
                name: `addresses[${index}].userName`,
            });
            $fieldset.find(`label[for^="addresses"][for$=".userName"]`).attr("for", `addresses${index}.userName`);

            // PostCode
            $fieldset.find(`input[name^="addresses"][name$=".postCode"]`).attr({
                id: `addresses${index}.postCode`,
                name: `addresses[${index}].postCode`,
            });
            $fieldset.find(`label[for^="addresses"][for$=".postCode"]`).attr("for", `addresses${index}.postCode`);

            // Address
            $fieldset.find(`input[name^="addresses"][name$=".address"]`).attr({
                id: `addresses${index}.address`,
                name: `addresses[${index}].address`,
            });
            $fieldset.find(`label[for^="addresses"][for$=".address"]`).attr("for", `addresses${index}.address`);

            // Remove button
            $fieldset.find('button[data-action="remove-address"]').attr("id", `remove${index}`);
        });
    }
}

$(function () {
    const addressesView = new AddressesView();

    $('[data-action="add-address"]').click(function (e) {
        e.preventDefault();
        addressesView.addAddress();
    });

    // Use event delegation to handle dynamically added elements as well
    $(document).on("click", '[data-action="remove-address"]', function (e) {
        e.preventDefault();

        const $this = $(this); // this button
        const $fieldset = $this.closest("fieldset.address"); // Get the closest fieldset

        if ($fieldset.length > 0) {
            addressesView.removeAddress($fieldset);
        } else {
            console.error("Could not find parent fieldset");
        }
    });
});
