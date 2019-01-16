import axios from "axios";
import qs from "querystring";
import Cookies from "js-cookie";

export const configs = {
  dev: {
    domain: "http://localhost:9666"
  },
  stg: {
    domain: "http://localhost:9666"
  },
  prd: {
    domain: "http://localhost:9666"
  }
};
export const env = window.pageEnv || "dev";

class ConfigUri {
  static getHeaders() {
    const bearer = "Bearer " + Cookies.get("token");
    return {
      mode: "no-cors",
      headers: {
        Accept: "application/json",
        Authorization: bearer,
        "Content-Type": "application/json"
      }
    };
  }
  static getHeadersLogin() {
    return {
      mode: "no-cors",
      headers: {
        Accept: "application/json",
        Authorization: "Basic dGVzdDp0ZXN0Mg==",
        "Content-Type": "application/x-www-form-urlencoded"
      }
    };
  }

  static getEndpoint(endPoint) {
    return configs[env].domain + endPoint;
  }
  static get(url) {
    return axios.get(url, { headers: this.getHeaders() });
  }
  static post(url, data) {
    return axios.post(url, data, this.getHeaders());
  }
  static login(url, data) {
    return axios.post(url, qs.stringify(data), this.getHeadersLogin());
  }
}
export const ConfigUriUtil = ConfigUri;

export const config = configs[env];
