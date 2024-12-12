package com.example.backend.restaurante.model.menu;

import java.util.List;

public class CardapioRequest {

        private String title;
        private List<Long> itemIds;


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Long> getItemIds() {
            return itemIds;
        }

        public void setItemIds(List<Long> itemIds) {
            this.itemIds = itemIds;
        }

}
