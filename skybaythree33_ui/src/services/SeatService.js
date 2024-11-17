import http from "../http-common"; 

class SeatService {
  getAllSeats(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/seat/seats`, searchDTO);
  }

  get(seatId) {
    return this.getRequest(`/seat/${seatId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/seat?field=${matchData}`, null);
  }

  addSeat(data) {
    return http.post("/seat/addSeat", data);
  }

  update(data) {
  	return http.post("/seat/updateSeat", data);
  }
  
  uploadImage(data,seatId) {
  	return http.postForm("/seat/uploadImage/"+seatId, data);
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

export default new SeatService();
