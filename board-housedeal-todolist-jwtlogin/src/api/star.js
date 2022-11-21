import { apiInstance } from "./index.js";

const api = apiInstance();

function listStars(userid, success, fail) {
  api.get(`/star/${userid}`).then(success).catch(fail);
}

// # 관심목록 조회, 추가, 삭제: userid, aptCode 필요
function addStar(param, success, fail) {
  api.post(`/star`, { params: param }).then(success).catch(fail);
}

function getStar(param, success, fail) {
  api.post(`/star/one`, { params: param }).then(success).catch(fail);
}

function deleteStar(param, success, fail) {
  api.delete(`/star`, { params: param }).then(success).catch(fail);
}
export { listStars, addStar, getStar, deleteStar };
