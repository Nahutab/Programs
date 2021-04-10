/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no.3
 */
public class BloodType {

    private String bloodType;

    public static boolean isCompatible(BloodType recipient, BloodType donor){

        if(recipient.bloodType.equals("O") && donor.bloodType.equals("A")){
            return false;
        }

        else if(recipient.bloodType.equals("O") && donor.bloodType.equals("B")){
            return false;
        }

        else if(recipient.bloodType.equals("O") && donor.bloodType.equals("AB")){
            return false;
        }

        else if(recipient.bloodType.equals("A") && donor.bloodType.equals("B")){
            return false;
        }

        else if(recipient.bloodType.equals("A") && donor.bloodType.equals("AB")){
            return false;
        }

        else if(recipient.bloodType.equals("B") && donor.bloodType.equals("A")){
            return false;
        }

        else if(recipient.bloodType.equals("B") && donor.bloodType.equals("AB")){
            return false;
        }
        else return true;
    }


    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getBloodType() {
        return bloodType;
    }
}
