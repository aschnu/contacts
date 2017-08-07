/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacts.contacts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AGRZEGOR
 */
@Repository
public class ContactRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public ContactRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Contact> findAll() {
        return jdbc.query("select *"
                + " from contacts order by lastName",
                new RowMapper<Contact>() {
            public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
                Contact contact = new Contact();
                contact.setId(rs.getString(1));
                contact.setFirstName(rs.getString(2));
                contact.setLastName(rs.getString(3));
                contact.setPhoneNumber(rs.getString(4));
                contact.setEmailAddress(rs.getString(5));
                return contact;
            }
        });
    }

    public void save(Contact contact) {
        jdbc.update("insert into contacts "
                + "(firstName, lastName, phoneNumber, emailAddress) "
                + "values (?, ?, ?, ?)", contact.getFirstName(), contact.getLastName(), 
                contact.getPhoneNumber(), contact.getEmailAddress());
    }

}
