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
            <ticket-table
            v-if="tickets && tickets.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:tickets="tickets"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-tickets="getAllTickets"
             >

            </ticket-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import TicketTable from "@/components/TicketTable";
import TicketService from "../services/TicketService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    TicketTable,
  },
  data() {
    return {
      tickets: [],
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
    async getAllTickets(sortBy='ticketId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await TicketService.getAllTickets(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.tickets.length) {
					this.tickets = response.data.tickets;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching tickets:", error);
        }
        
      } catch (error) {
        console.error("Error fetching ticket details:", error);
      }
    },
  },
  mounted() {
    this.getAllTickets();
  },
  created() {
    this.$root.$on('searchQueryForTicketsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllTickets();
    })
  }
};
</script>
<style></style>
