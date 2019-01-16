import { ConfigUriUtil } from "../config/index.jsx";

const endpoints = {
  login: ConfigUriUtil.getEndpoint("/oauth/token")
};

export default {
  login(email, password) {
    return ConfigUriUtil.login(endpoints.login, {
      grant_type: "password",
      username: email,
      password
    });
  }
};
