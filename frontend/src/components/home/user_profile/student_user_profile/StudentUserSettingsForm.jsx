import CheckboxComponent from "../../general_form_components/CheckboxComponent";
import SaveCancelComponent from "../../general_form_components/SaveCancelComponent";


export default function StudentUserSettingsForm(
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

                    {/* Privacity Settings Fields */}
                    <div className="w-1/2">
                        <h1 className="text-lg font-bold">
                            Privacitat
                        </h1>

                        <div className="w-full text-sm grid gap-1 p-1">
                            <CheckboxComponent 
                                text="Correu electrònic públic" action={handleInput} 
                                value={formData.publicEmail} name={"publicEmail"}/>

                            <CheckboxComponent 
                                text="Telèfon públic" action={handleInput} 
                                value={formData.publicPhone} name={"publicPhone"}/>

                            <CheckboxComponent 
                                text="LinkedIn públic" action={handleInput} 
                                value={formData.publicLinkedinUrl} name={"publicLinkedinUrl"}/>

                            <CheckboxComponent 
                                text="Portfolio públic" action={handleInput} 
                                value={formData.publicPortfolioUrl} name={"publicPortfolioUrl"}/>

                            <CheckboxComponent 
                                text="YouTube públic" action={handleInput} 
                                value={formData.publicYoutubeUrl} name={"publicYoutubeUrl"}/>

                            <CheckboxComponent 
                                text="GitHub públic" action={handleInput} 
                                value={formData.publicGithubUrl} name={"publicGithubUrl"}/>
                        </div>
                    </div>

                    {/* Notifications Settings Fields */}
                    <div className="w-1/2">
                        <h1 className="text-lg font-bold">
                            Notificacions
                        </h1>

                        <div className="w-full text-sm grid gap-1 p-1">
                            <CheckboxComponent 
                                text="Nova oferta" action={handleInput} 
                                value={formData.newOfferNotification} name={"newOfferNotification"}/>

                            <CheckboxComponent 
                                text="Inscripció a una oferta" action={handleInput} 
                                value={formData.newInscriptionNotification} name={"newInscriptionNotification"}/>
                        </div>
                    </div>
                        
                </div>

            </div>

            <br/><br/>

            <SaveCancelComponent/>

        </form>
    )
}