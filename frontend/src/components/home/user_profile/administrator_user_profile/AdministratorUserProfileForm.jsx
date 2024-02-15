import FieldComponent from "../../general_form_components/FieldComponent"
import SaveCancelComponent from "../../general_form_components/SaveCancelComponent"



export default function AdministratorUserProfileForm(
    {formData, handleInput, handleSubmit}
) {

    return (
        <form onSubmit={handleSubmit} className="py-5">
            <h1 className="text-2xl font-bold">
                Perfil
            </h1>

            <br/>

            {/* Name and Surname Fields */}
            <div className="flex gap-5">
                <FieldComponent title="Nom" name="name" 
                    action={handleInput} value={formData.name} 
                    necessary/>

                <FieldComponent title="Telèfon" name="phone"
                    action={handleInput} value={formData.phone} 
                    necessary/>
            </div>

            <br/>

            {/* Email field */}
            <div className="grid">
                <FieldComponent title="Correu electrònic" name="email"
                    action={handleInput} value={formData.email}/>
                <br/>
            </div>

            <br/>

            <SaveCancelComponent/>
        </form>
    )
}