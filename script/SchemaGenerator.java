import java.io.FileOutputStream;
import java.io.IOException;

/** Schema generator. */
public class SchemaGenerator {

  /** Main Method. */
  public static void main(String[] args) {

    createUserSql();
  }

  /** User Init generator. */
  public static void createUserSql() {
    try {
      FileOutputStream out = new FileOutputStream("users.sql");

      int userCount = 0;

      for (int i = 0; i < 50; i++) {
        String role = i % 2 == 0 ? "ADMIN" : "USER";
        String name = "User" + userCount;
        String username = "UserName" + ++userCount;

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO users(name, username, role) VALUES('")
            .append(name)
            .append("','")
            .append(username)
            .append("','")
            .append(role)
            .append("');")
            .append("\n");

        String sql = sb.toString();
        out.write(sql.getBytes());
      }

      out.write("-- USER WITH DIFFERENT LOCK\n".getBytes());
      for (int i = 0; i < 50; i++) {
        String role = i % 2 == 0 ? "ADMIN" : "USER";
        String name = "User" + userCount;
        String username = "UserName" + ++userCount;
        boolean isAccountLocked = i % 2 == 0 ? true : false;
        boolean isEnabled = i % 2 == 0 ? true : false;

        StringBuilder sb = new StringBuilder();
        sb.append(
            "INSERT INTO users(name, username, role, is_account_non_locked, is_enabled)"
                + " VALUES('")
            .append(name)
            .append("','")
            .append(username)
            .append("','")
            .append(role)
            .append("',")
            .append(isAccountLocked)
            .append(",")
            .append(isEnabled)
            .append(");")
            .append("\n");

        String sql = sb.toString();
        out.write(sql.getBytes());
      }

      out.close();
    } catch (IOException e) {
    }
  }
}
