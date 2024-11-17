import http from "../http-common"; 

class BoardingPassService {
  getAllBoardingPasss(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/boardingPass/boardingPasss`, searchDTO);
  }

  get(boardingPassId) {
    return this.getRequest(`/boardingPass/${boardingPassId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/boardingPass?field=${matchData}`, null);
  }

  addBoardingPass(data) {
    return http.post("/boardingPass/addBoardingPass", data);
  }

  update(data) {
  	return http.post("/boardingPass/updateBoardingPass", data);
  }
  
  uploadImage(data,boardingPassId) {
  	return http.postForm("/boardingPass/uploadImage/"+boardingPassId, data);
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

export default new BoardingPassService();
