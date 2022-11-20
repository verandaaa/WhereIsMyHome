import { apiInstance } from "./index.js";

const api = apiInstance();

function listStars(userid, success, fail) {
  api.get(`/star/${userid}`).then(success).catch(fail);
}
export { listStars };
