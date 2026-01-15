import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/** SQL schema data generator. */
public final class SchemaGenerator {

  private static final int USER_COUNT = 100;
  private static final int GROUP_COUNT = 10;
  private static final int CHAT_COUNT = 2000;

  public static void main(String[] args) {
    generateUsers();
    generateGroups();
    generateNormalChats();
    generateGroupChats();
  }

  /* ========================= USERS ========================= */

  private static void generateUsers() {
    try (FileOutputStream out = new FileOutputStream("users.sql")) {

      for (int i = 1; i <= USER_COUNT; i++) {
        String role = (i % 2 == 0) ? "ADMIN" : "USER";
        boolean isAccountNonLocked = i % 3 != 0;
        boolean isEnabled = i % 5 != 0;

        String sql =
            "INSERT INTO users(name, username, email, role, is_account_non_locked, is_enabled)"
                + " VALUES ('User"
                + i
                + "','"
                + "username"
                + i
                + "','"
                + "user"
                + i
                + "@gmail.com','"
                + role
                + "',"
                + isAccountNonLocked
                + ","
                + isEnabled
                + ");\n";

        out.write(sql.getBytes(StandardCharsets.UTF_8));
      }

    } catch (IOException e) {
      throw new RuntimeException("Failed to generate users.sql", e);
    }
  }

  /* ========================= GROUPS ========================= */

  private static void generateGroups() {
    try (FileOutputStream out = new FileOutputStream("groups.sql")) {

      for (int i = 1; i <= GROUP_COUNT; i++) {
        int creatorId = (i % USER_COUNT) + 1;

        String sql =
            "INSERT INTO groups(name, description, creator) VALUES ('"
                + "Group "
                + i
                + "','"
                + "This is test group "
                + i
                + "',"
                + creatorId
                + ");\n";

        out.write(sql.getBytes(StandardCharsets.UTF_8));
      }

    } catch (IOException e) {
      throw new RuntimeException("Failed to generate groups.sql", e);
    }
  }

  /* ========================= NORMAL CHAT ========================= */

  private static void generateNormalChats() {
    try (FileOutputStream out = new FileOutputStream("normal_chat.sql")) {

      for (int i = 1; i <= CHAT_COUNT; i++) {
        int sentBy = (i % USER_COUNT) + 1;
        int sentTo = ((i + 7) % USER_COUNT) + 1;

        String message = "Direct message #" + i + " from user " + sentBy;

        String sql =
            "INSERT INTO normal_chat(message, sent_by, sent_to) VALUES ('"
                + message
                + "',"
                + sentBy
                + ","
                + sentTo
                + ");\n";

        out.write(sql.getBytes(StandardCharsets.UTF_8));
      }

    } catch (IOException e) {
      throw new RuntimeException("Failed to generate normal_chat.sql", e);
    }
  }

  /* ========================= GROUP CHAT ========================= */

  private static void generateGroupChats() {
    try (FileOutputStream out = new FileOutputStream("group_chat.sql")) {

      for (int i = 1; i <= CHAT_COUNT; i++) {
        int sentBy = (i % USER_COUNT) + 1;
        int groupId = (i % GROUP_COUNT) + 1;

        String message = "Group message #" + i + " in group " + groupId;

        String sql =
            "INSERT INTO group_chat(message, sent_by, sent_to) VALUES ('"
                + message
                + "',"
                + sentBy
                + ","
                + groupId
                + ");\n";

        out.write(sql.getBytes(StandardCharsets.UTF_8));
      }

    } catch (IOException e) {
      throw new RuntimeException("Failed to generate group_chat.sql", e);
    }
  }
}
