package fw.profileservice.model;

public enum SectorList {
    IT,
    FITNESS,
    FINANCE,
    AUTOMOTIVE,
    LIFESTYLE,
    SCIENCE,
    MUSIC,
    PETS_AND_ANIMALS {
        @Override
        public String toString() {
            return "PETS AND ANIMALS";
        }
    },
    SPORTS,
    GYM_AND_FITNESS {
        @Override
        public String toString() {
            return "GYM AND FITNESS";
        }
    },
    GAMING,
    FOOD_AND_DRINK {
        @Override
        public String toString() {
            return "FOOD AND DRINK";
        }
    },
    FASHION,
    TRAVEL,
    BEAUTY,
    ART
}
