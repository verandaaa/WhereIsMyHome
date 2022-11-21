import { apiInstance } from "./index.js";

const api = apiInstance();

function listStars(userid, success, fail) {
  api.get(`/star/${userid}`).then(success).catch(fail);
}

// # 관심목록 추가: userid, aptCode 필요
// function addStar(param, success, fail) {
//   api.delete(`/star`, {params: param}).then(success).catch(fail);
// }

function deleteStar(starNo, success, fail) {
  api.delete(`/star/${starNo}`).then(success).catch(fail);
}
export { listStars, deleteStar };
