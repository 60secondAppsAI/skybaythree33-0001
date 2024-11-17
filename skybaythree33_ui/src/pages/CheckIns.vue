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
            <checkIn-table
            v-if="checkIns && checkIns.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:checkIns="checkIns"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-check-ins="getAllCheckIns"
             >

            </checkIn-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import CheckInTable from "@/components/CheckInTable";
import CheckInService from "../services/CheckInService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CheckInTable,
  },
  data() {
    return {
      checkIns: [],
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
    async getAllCheckIns(sortBy='checkInId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CheckInService.getAllCheckIns(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.checkIns.length) {
					this.checkIns = response.data.checkIns;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching checkIns:", error);
        }
        
      } catch (error) {
        console.error("Error fetching checkIn details:", error);
      }
    },
  },
  mounted() {
    this.getAllCheckIns();
  },
  created() {
    this.$root.$on('searchQueryForCheckInsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllCheckIns();
    })
  }
};
</script>
<style></style>
