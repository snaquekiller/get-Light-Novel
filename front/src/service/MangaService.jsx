import { ConfigUriUtil } from "../config/index.jsx";

const endpoints = {
  mangaList: ConfigUriUtil.getEndpoint("/manga"),
  scrapManga: ConfigUriUtil.getEndpoint("/scrap")
};

export default {
  scrapManga(url) {
    return ConfigUriUtil.post(endpoints.scrapManga, { url });
  }
};
