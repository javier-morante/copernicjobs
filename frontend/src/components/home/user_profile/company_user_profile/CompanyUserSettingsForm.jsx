import CheckboxComponent from "../../general_form_components/CheckboxComponent"
import SaveCancelComponent from "../../general_form_components/SaveCancelComponent"


export default function CompanyUserSettingsForm(
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
                            Notifications
                        </h1>

                        <div className="w-full text-sm grid gap-1 p-1">
                            <CheckboxComponent 
                                text="Incidència resolta" action={handleInput} 
                                value={formData.resolvedReportNotification} name={"resolvedReportNotification"}/>
                        </div>
                    </div>
                        
                </div>

            </div>

            <br/><br/>

            <SaveCancelComponent/>

        </form>
    )
}