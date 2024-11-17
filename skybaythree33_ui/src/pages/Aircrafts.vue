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
            <aircraft-table
            v-if="aircrafts && aircrafts.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:aircrafts="aircrafts"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-aircrafts="getAllAircrafts"
             >

            </aircraft-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import AircraftTable from "@/components/AircraftTable";
import AircraftService from "../services/AircraftService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    AircraftTable,
  },
  data() {
    return {
      aircrafts: [],
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
    async getAllAircrafts(sortBy='aircraftId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await AircraftService.getAllAircrafts(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.aircrafts.length) {
					this.aircrafts = response.data.aircrafts;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching aircrafts:", error);
        }
        
      } catch (error) {
        console.error("Error fetching aircraft details:", error);
      }
    },
  },
  mounted() {
    this.getAllAircrafts();
  },
  created() {
    this.$root.$on('searchQueryForAircraftsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllAircrafts();
    })
  }
};
</script>
<style></style>
