import http from "../http-common"; 

class CheckInService {
  getAllCheckIns(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/checkIn/checkIns`, searchDTO);
  }

  get(checkInId) {
    return this.getRequest(`/checkIn/${checkInId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/checkIn?field=${matchData}`, null);
  }

  addCheckIn(data) {
    return http.post("/checkIn/addCheckIn", data);
  }

  update(data) {
  	return http.post("/checkIn/updateCheckIn", data);
  }
  
  uploadImage(data,checkInId) {
  	return http.postForm("/checkIn/uploadImage/"+checkInId, data);
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

export default new CheckInService();
