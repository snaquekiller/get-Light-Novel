import { ConfigUriUtil } from "../config/index.jsx";
import Cookies from "js-cookie";

const endpoints = {
  login: ConfigUriUtil.getEndpoint("/oauth/token")
};

export default {
  login(email, password) {
    const loginPromise = ConfigUriUtil.login(endpoints.login, {
      grant_type: "password",
      username: email,
      password
    });

    loginPromise.then(response => {
      if (response.data) {
        const token = response.data.access_token;
        Cookies.set("token", token, { expires: 7 });
      }
    });

    return loginPromise;
  }
};
