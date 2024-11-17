import http from "../http-common"; 

class TicketService {
  getAllTickets(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/ticket/tickets`, searchDTO);
  }

  get(ticketId) {
    return this.getRequest(`/ticket/${ticketId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/ticket?field=${matchData}`, null);
  }

  addTicket(data) {
    return http.post("/ticket/addTicket", data);
  }

  update(data) {
  	return http.post("/ticket/updateTicket", data);
  }
  
  uploadImage(data,ticketId) {
  	return http.postForm("/ticket/uploadImage/"+ticketId, data);
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

export default new TicketService();
