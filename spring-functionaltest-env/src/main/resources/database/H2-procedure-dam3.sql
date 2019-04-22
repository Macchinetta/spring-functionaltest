DROP ALIAS IF EXISTS HALF_SALE~

CREATE ALIAS HALF_SALE AS $$
int halfSale(Connection conn) throws SQLException {
    return conn.createStatement().executeUpdate("UPDATE t_book t1 SET price = (SELECT price/2 FROM t_book t2 WHERE t1.book_id = t2.book_id)");
} $$~
