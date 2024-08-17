public class Enums{
     enum TypeOfMilk {
        FULL_CREAM("Full-cream"),
        SOY("Soy"),
        SKIM("Skim"),
        OAT("Oat"),
        ALMOND("Almond"),
        COCONUT("Coconut"),
        SKIP("Skip");
    
        private final String value;
    
        TypeOfMilk(String value) {
            this.value = value;
        }
    
        public String getValue() {
            return value;
        }
    }
    
     enum BoilingTemperature {
        EIGHTY("80"),
        EIGHTY_FIVE("85"),
        NINETY("90"),
        NINETY_FIVE("95"),
        HUNDRED("100"),
        SKIP("Skip");
    
        private final String value;
    
        BoilingTemperature(String value) {
            this.value = value;
        }
    
        public String getValue() {
            return value;
        }
    }

    enum TeaSteepingTime {
        ONE("1"),
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5"),
        SIX("6"),
        SEVEN("7"),
        EIGHT("8"),
        SKIP("Skip");
    
        private final String value;
    
        TeaSteepingTime(String value) {
            this.value = value;
        }
    
        public String getValue() {
            return value;
        }
    }
    
     enum BeverageTypeOptions {
        COFFEE("coffee"),
        TEA("tea");
    
        private final String value;
    
        BeverageTypeOptions(String value) {
            this.value = value;
        }
    
        public String getValue() {
            return value;
        }
    }
}