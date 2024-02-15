import CheckboxComponent from "../../../home/general_form_components/CheckboxComponent";
import SaveCancelComponent from "../../../home/general_form_components/SaveCancelComponent";

export default function AdministratorRoleManagementForms(
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
        
            <CheckboxComponent text="Sol·licituds" action={handleInput} 
                value={formData.requests} name="requests"/>

            <CheckboxComponent text="Usuaris" action={handleInput} 
                value={formData.users} name="users"/>
                
            <CheckboxComponent text="Informes" action={handleInput} 
                value={formData.reports} name="reports"/>

            <br/><br/>

            <SaveCancelComponent/>
            
        </form>

    )
}