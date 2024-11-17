import http from "../http-common"; 

class MealService {
  getAllMeals(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/meal/meals`, searchDTO);
  }

  get(mealId) {
    return this.getRequest(`/meal/${mealId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/meal?field=${matchData}`, null);
  }

  addMeal(data) {
    return http.post("/meal/addMeal", data);
  }

  update(data) {
  	return http.post("/meal/updateMeal", data);
  }
  
  uploadImage(data,mealId) {
  	return http.postForm("/meal/uploadImage/"+mealId, data);
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

export default new MealService();
