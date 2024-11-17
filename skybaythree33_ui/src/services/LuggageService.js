import http from "../http-common"; 

class LuggageService {
  getAllLuggages(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/luggage/luggages`, searchDTO);
  }

  get(luggageId) {
    return this.getRequest(`/luggage/${luggageId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/luggage?field=${matchData}`, null);
  }

  addLuggage(data) {
    return http.post("/luggage/addLuggage", data);
  }

  update(data) {
  	return http.post("/luggage/updateLuggage", data);
  }
  
  uploadImage(data,luggageId) {
  	return http.postForm("/luggage/uploadImage/"+luggageId, data);
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

export default new LuggageService();
