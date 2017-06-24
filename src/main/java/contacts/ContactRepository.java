/**
 * 
 */
package contacts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author sunff
 * 
 */

@Repository
public class ContactRepository {

	@Autowired
	private JdbcTemplate jdbc;

	public List<Contact> findAll() {
		return jdbc
				.query("select id,firstName,lastName,phoneNumber,emailAddress from contacts order by lastName",
						new RowMapper<Contact>() {

							@Override
							public Contact mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								Contact c = new Contact();
								c.setId(rs.getLong("id"));
								c.setFirstName(rs.getString("firstName"));
								c.setLastName(rs.getString("lastName"));
								c.setPhoneNumber(rs.getString("phoneNumber"));
								c.setEmailAddress(rs.getString("emailAddress"));

								return c;
							}

						}

				);
	}

	public void save(Contact contact) {
		jdbc.update(
				"insert into contacts(firstName,lastName,phoneNumber,emailAddress) values(?,?,?,?)",
				contact.getFirstName(), contact.getLastName(),
				contact.getPhoneNumber(), contact.getEmailAddress());
	}
}
