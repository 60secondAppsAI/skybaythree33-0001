<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <meal-table
            v-if="meals && meals.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:meals="meals"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-meals="getAllMeals"
             >

            </meal-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import MealTable from "@/components/MealTable";
import MealService from "../services/MealService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    MealTable,
  },
  data() {
    return {
      meals: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllMeals(sortBy='mealId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await MealService.getAllMeals(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.meals.length) {
					this.meals = response.data.meals;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching meals:", error);
        }
        
      } catch (error) {
        console.error("Error fetching meal details:", error);
      }
    },
  },
  mounted() {
    this.getAllMeals();
  },
  created() {
    this.$root.$on('searchQueryForMealsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllMeals();
    })
  }
};
</script>
<style></style>
