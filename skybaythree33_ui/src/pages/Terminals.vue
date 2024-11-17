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
            <terminal-table
            v-if="terminals && terminals.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:terminals="terminals"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-terminals="getAllTerminals"
             >

            </terminal-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import TerminalTable from "@/components/TerminalTable";
import TerminalService from "../services/TerminalService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    TerminalTable,
  },
  data() {
    return {
      terminals: [],
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
    async getAllTerminals(sortBy='terminalId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await TerminalService.getAllTerminals(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.terminals.length) {
					this.terminals = response.data.terminals;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching terminals:", error);
        }
        
      } catch (error) {
        console.error("Error fetching terminal details:", error);
      }
    },
  },
  mounted() {
    this.getAllTerminals();
  },
  created() {
    this.$root.$on('searchQueryForTerminalsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllTerminals();
    })
  }
};
</script>
<style></style>
