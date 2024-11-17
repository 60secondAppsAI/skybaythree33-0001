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
            <seat-table
            v-if="seats && seats.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:seats="seats"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-seats="getAllSeats"
             >

            </seat-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import SeatTable from "@/components/SeatTable";
import SeatService from "../services/SeatService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    SeatTable,
  },
  data() {
    return {
      seats: [],
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
    async getAllSeats(sortBy='seatId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await SeatService.getAllSeats(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.seats.length) {
					this.seats = response.data.seats;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching seats:", error);
        }
        
      } catch (error) {
        console.error("Error fetching seat details:", error);
      }
    },
  },
  mounted() {
    this.getAllSeats();
  },
  created() {
    this.$root.$on('searchQueryForSeatsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllSeats();
    })
  }
};
</script>
<style></style>
