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
            <frequentFlyer-table
            v-if="frequentFlyers && frequentFlyers.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:frequentFlyers="frequentFlyers"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-frequent-flyers="getAllFrequentFlyers"
             >

            </frequentFlyer-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import FrequentFlyerTable from "@/components/FrequentFlyerTable";
import FrequentFlyerService from "../services/FrequentFlyerService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    FrequentFlyerTable,
  },
  data() {
    return {
      frequentFlyers: [],
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
    async getAllFrequentFlyers(sortBy='frequentFlyerId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await FrequentFlyerService.getAllFrequentFlyers(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.frequentFlyers.length) {
					this.frequentFlyers = response.data.frequentFlyers;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching frequentFlyers:", error);
        }
        
      } catch (error) {
        console.error("Error fetching frequentFlyer details:", error);
      }
    },
  },
  mounted() {
    this.getAllFrequentFlyers();
  },
  created() {
    this.$root.$on('searchQueryForFrequentFlyersChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllFrequentFlyers();
    })
  }
};
</script>
<style></style>
