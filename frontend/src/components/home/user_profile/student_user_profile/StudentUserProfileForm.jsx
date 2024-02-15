import FieldComponent from "../../general_form_components/FieldComponent"
import SaveCancelComponent from "../../general_form_components/SaveCancelComponent"


export default function StudentUserProfileForm(
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
                <FieldComponent title="Nom" name="name" type="text" 
                    action={handleInput} value={formData.name} 
                    necessary/>

                <FieldComponent title="Cognom" name="surname" type="text"
                    action={handleInput} value={formData.surname} 
                    necessary/>
            </div>

            <br/>

            {/* Phone and Mail Fields */}
            <div className="grid">

                <FieldComponent title="NIF" name="nif" type="text"
                    action={handleInput} value={formData.nif}
                    necessary/>

                <br/>

                <FieldComponent title="Telèfon" name="phone" type="phone" 
                    action={handleInput} value={formData.phone}
                    necessary/>
                
                <br/>

                <FieldComponent title="Correu electrònic" name="email" type="email"
                    action={handleInput} value={formData.email}
                    necessary/>

            </div>

            <br/><br/>

            <SaveCancelComponent/>
        </form>
    )
}