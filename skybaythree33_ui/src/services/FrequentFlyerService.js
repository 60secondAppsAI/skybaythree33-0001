import http from "../http-common"; 

class FrequentFlyerService {
  getAllFrequentFlyers(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/frequentFlyer/frequentFlyers`, searchDTO);
  }

  get(frequentFlyerId) {
    return this.getRequest(`/frequentFlyer/${frequentFlyerId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/frequentFlyer?field=${matchData}`, null);
  }

  addFrequentFlyer(data) {
    return http.post("/frequentFlyer/addFrequentFlyer", data);
  }

  update(data) {
  	return http.post("/frequentFlyer/updateFrequentFlyer", data);
  }
  
  uploadImage(data,frequentFlyerId) {
  	return http.postForm("/frequentFlyer/uploadImage/"+frequentFlyerId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new FrequentFlyerService();
