import { apiInstance } from "./index.js";
// import { apiInstance, houseInstance } from "./index.js";

const api = apiInstance();
// const house = houseInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/map/dong`, { params: params }).then(success).catch(fail);
}

// # 공공데이터 API 이용
// function houseList(params, success, fail) {
//   house.get(``, { params: params }).then(success).catch(fail);
// }

// # DB 이용
function houseList(params, success, fail) {
  api.get(`/map/house`, { params: params }).then(success).catch(fail);
}

function houseList2(params, success, fail) {
  api.get(`/map/house2`, { params: params }).then(success).catch(fail);
}

function getYearlyPrice(param, success, fail) {
  api.get(`/deal/yearlyPrice/${param}`).then(success).catch(fail);
}

export { sidoList, gugunList, dongList, houseList, houseList2, getYearlyPrice };
