import CheckboxComponent from "../../../home/general_form_components/CheckboxComponent";
import SaveCancelComponent from "../../../home/general_form_components/SaveCancelComponent";

export default function CompanyRoleManagementForms(
    {formData, handleInput, handleSubmit}
) {
    return (
        <form onSubmit={handleSubmit}>
            <CheckboxComponent text="Les meves ofertes" action={handleInput} 
                value={formData.myOffers} name="myOffers"/>

            <CheckboxComponent text="Crear oferta" action={handleInput} 
                value={formData.createOffer} name="createOffer"/>

            <CheckboxComponent text="Incidències" action={handleInput} 
                value={formData.incidents} name="incidents"/>
                
            <CheckboxComponent text="Ofertes" action={handleInput} 
                value={formData.offers} name="offers"/>

            <br/><br/>

            <SaveCancelComponent/>
            
        </form>

    )
}