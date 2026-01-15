package com.lothric.backend.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lothric.backend.AbstractTestContainer;
import com.lothric.backend.shared.constant.RestConstant;
import com.lothric.backend.user.domain.exception.UserExceptionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/** Testing the controller layer of the User. */
@AutoConfigureMockMvc
public class UserControllerTest extends AbstractTestContainer {

  @Autowired private MockMvc mockMvc;
  private final String v1 = "/api/v1/user";

  private final Long foundUserId = 1L;
  private final Long notFoundUserId = 9999L;
  private final String uniqueUserName = "UserName3";
  private final String uniqueEmail = "user4@gmail.com";
  private final String req =
      """
        {
      "name": "Tester1",
      "username": "Tester-1",
      "email": "tester@gmail.com",
      "role": "ADMIN",
      "isAccountNonLocked": true,
      "isEnabled": false
        }
      """;
  private final String userNameUniqueReq = req.replace("Tester-1", uniqueUserName);
  private final String emailUniqueReq = req.replace("tester@gmail.com", uniqueEmail);

  @Test
  @Transactional
  @WithMockUser
  void getAll() throws Exception {
    this.mockMvc
        .perform(get(v1))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.data").isArray());
  }

  @Test
  @Transactional
  @WithMockUser
  void getById() throws Exception {
    this.mockMvc
        .perform(get(v1 + "/" + foundUserId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.data.id").value(foundUserId));
  }

  @Test
  @Transactional
  @WithMockUser
  void create() throws Exception {
    this.mockMvc
        .perform(
            post(v1)
                .content(req)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.data").value(RestConstant.CREATED));
  }

  @Test
  @Transactional
  @WithMockUser
  void update() throws Exception {
    this.mockMvc
        .perform(
            patch(v1 + "/" + foundUserId)
                .content(req)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.data").value(RestConstant.UPDATED));
  }

  @Test
  @Transactional
  @WithMockUser
  void deleteById() throws Exception {
    this.mockMvc
        .perform(delete(v1 + "/" + foundUserId).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.data").value(RestConstant.DELETED));
  }

  @Test
  @Transactional
  @WithMockUser
  void user_notFound() throws Exception {
    this.mockMvc
        .perform(get(v1 + "/" + notFoundUserId))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.code").value(UserExceptionType.USER_NOT_FOUND.name()));
    this.mockMvc
        .perform(
            patch(v1 + "/" + notFoundUserId).contentType(MediaType.APPLICATION_JSON).content(req))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.code").value(UserExceptionType.USER_NOT_FOUND.name()));
    this.mockMvc
        .perform(delete(v1 + "/" + notFoundUserId))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.code").value(UserExceptionType.USER_NOT_FOUND.name()));
  }

  @Test
  @Transactional
  @WithMockUser
  void user_username_unique() throws Exception {
    this.mockMvc
        .perform(
            post(v1)
                .content(userNameUniqueReq)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.code").value(UserExceptionType.USER_NAME_MUST_BE_UNIQUE.name()));
  }

  @Test
  @Transactional
  @WithMockUser
  void user_username_unique_update() throws Exception {

    this.mockMvc
        .perform(
            patch(v1 + "/" + foundUserId)
                .content(userNameUniqueReq)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.code").value(UserExceptionType.USER_NAME_MUST_BE_UNIQUE.name()));
  }

  @Test
  @Transactional
  @WithMockUser
  void user_email_unique() throws Exception {

    this.mockMvc
        .perform(
            post(v1)
                .content(emailUniqueReq)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.code").value(UserExceptionType.USER_EMAIL_MUST_BE_UNIQUE.name()));
  }

  @Test
  @Transactional
  @WithMockUser
  void user_email_unique_update() throws Exception {

    this.mockMvc
        .perform(
            patch(v1 + "/" + foundUserId)
                .content(emailUniqueReq)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.code").value(UserExceptionType.USER_EMAIL_MUST_BE_UNIQUE.name()));
  }
}
