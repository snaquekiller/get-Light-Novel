import axios from "axios";

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
    return {
      mode: "no-cors",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
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
}
export const ConfigUriUtil = ConfigUri;

export const config = configs[env];
