import FieldComponent from "../../../home/general_form_components/FieldComponent";

export default function StudentManagementUpdateForm(
    {managePopup, handleSubmit, handleInput, formData}
) {
    
    return <form onSubmit={handleSubmit} className="w-screen h-screen top-0 left-0 z-50 
        fixed bg-black bg-opacity-75 flex place-items-center justify-center">


        <div className="lg:w-6/12 w-8/12 bg-white p-5 grid gap-2 rounded-md">

            <h1 className="text-3xl mb-10">
                Editar estudiant
            </h1>
            
            <div className="flex gap-2">
                <FieldComponent title="Nom" name="name" type="text"
                    value={formData.name} action={handleInput} necessary/>
        
                <FieldComponent title="Cognom" name="surname" type="text"
                    value={formData.surname} action={handleInput} necessary/>
            </div>

            <FieldComponent title="NIF" name="nif" type="text"
                value={formData.nif} action={handleInput} necessary/>

            <FieldComponent title="Telèfon" name="phone" type="phone"
                value={formData.phone} action={handleInput} necessary/>

            <FieldComponent title="Correu electrònic" name="email" type="email"
                    value={formData.email} action={handleInput} necessary/>

            <div className="w-full flex gap-5 mt-10">
                <button type="submit" className="w-full p-1 bg-valid_green rounded-md
                    hover:scale-105 transition text-white">
                    Crear
                </button>

                <button onClick={managePopup} 
                    className="w-full p-1 bg-warning_red rounded-md
                    hover:scale-105 transition text-white">
                    Cancel·lar
                </button>
            </div>
        </div>
    </form>
}