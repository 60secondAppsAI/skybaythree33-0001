import http from "../http-common"; 

class AircraftService {
  getAllAircrafts(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/aircraft/aircrafts`, searchDTO);
  }

  get(aircraftId) {
    return this.getRequest(`/aircraft/${aircraftId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/aircraft?field=${matchData}`, null);
  }

  addAircraft(data) {
    return http.post("/aircraft/addAircraft", data);
  }

  update(data) {
  	return http.post("/aircraft/updateAircraft", data);
  }
  
  uploadImage(data,aircraftId) {
  	return http.postForm("/aircraft/uploadImage/"+aircraftId, data);
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

export default new AircraftService();
