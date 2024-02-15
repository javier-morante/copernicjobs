import CheckboxComponent from "../../general_form_components/CheckboxComponent";
import SaveCancelComponent from "../../general_form_components/SaveCancelComponent";


export default function AdministratorUserSettingsForm(
    {formData, handleInput, handleSubmit}
) {
    return (
        <form onSubmit={handleSubmit}>
            <h1 className="text-2xl font-bold">
                Configuració
            </h1>

            <br/>

            <div className="sm:px-2">
                <div className="w-full sm:flex grid gap-5">

                    {/* Notifications Settings Fields */}
                    <div className="w-2/2">
                        <h1 className="text-lg font-bold">
                            Notificacions
                        </h1>

                        <div className="w-full text-sm grid gap-1 p-1">
                            <CheckboxComponent 
                                text="Nova sol·licitud d'accés" action={handleInput} 
                                value={formData.newAccessRequestNotification} name={"newAccessRequestNotification"}/>

                            <CheckboxComponent 
                                text="Nova incidència" action={handleInput} 
                                value={formData.newReportNotification} name={"newReportNotification"}/>
                        
                        </div>
                    </div>
                        
                </div>

            </div>

            <br/><br/>

            <SaveCancelComponent/>


        </form>
    )
}