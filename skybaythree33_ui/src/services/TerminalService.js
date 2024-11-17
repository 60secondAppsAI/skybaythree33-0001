import http from "../http-common"; 

class TerminalService {
  getAllTerminals(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/terminal/terminals`, searchDTO);
  }

  get(terminalId) {
    return this.getRequest(`/terminal/${terminalId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/terminal?field=${matchData}`, null);
  }

  addTerminal(data) {
    return http.post("/terminal/addTerminal", data);
  }

  update(data) {
  	return http.post("/terminal/updateTerminal", data);
  }
  
  uploadImage(data,terminalId) {
  	return http.postForm("/terminal/uploadImage/"+terminalId, data);
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

export default new TerminalService();
