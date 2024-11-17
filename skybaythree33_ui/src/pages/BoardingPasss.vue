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
            <boardingPass-table
            v-if="boardingPasss && boardingPasss.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:boardingPasss="boardingPasss"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-boarding-passs="getAllBoardingPasss"
             >

            </boardingPass-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import BoardingPassTable from "@/components/BoardingPassTable";
import BoardingPassService from "../services/BoardingPassService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    BoardingPassTable,
  },
  data() {
    return {
      boardingPasss: [],
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
    async getAllBoardingPasss(sortBy='boardingPassId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await BoardingPassService.getAllBoardingPasss(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.boardingPasss.length) {
					this.boardingPasss = response.data.boardingPasss;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching boardingPasss:", error);
        }
        
      } catch (error) {
        console.error("Error fetching boardingPass details:", error);
      }
    },
  },
  mounted() {
    this.getAllBoardingPasss();
  },
  created() {
    this.$root.$on('searchQueryForBoardingPasssChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllBoardingPasss();
    })
  }
};
</script>
<style></style>
