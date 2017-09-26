package BusinessModels;

public class KingCard extends Card{

    InstructionType[] instructionTypes;
    KingCardType kingCardType;

    public KingCard(){
        this.instructionTypes = new InstructionType[2];
    }

    public KingCardType getKingCardType() {
        return kingCardType;
    }

    public void setKingCardType(KingCardType kingCardType) {
        this.kingCardType = kingCardType;
    }

    public InstructionType[] getInstructionTypes() {
        return instructionTypes;
    }

    public void addInstructionTypes(InstructionType instructionType) throws Exception {
        if(instructionTypes[0] == null){
            instructionTypes[0] = instructionType;
        }else if(instructionTypes[1] == null){
            instructionTypes[1] = instructionType;
        }else {
            throw new Exception("Can not add more than 2 InstructionType to Base_Type KingCard");
        }
        this.instructionTypes = instructionTypes;
    }



}
