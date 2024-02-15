import FieldComponent from "../../../home/general_form_components/FieldComponent";

export default function CompanyManagementPasswordUpdateForm (
    {managePopup, handleSubmit, handleInput, formData}
) {
    
    return <form onSubmit={handleSubmit} className="w-screen h-screen top-0 left-0 z-50 
        fixed bg-black bg-opacity-75 flex place-items-center justify-center">

        <div className="lg:w-6/12 w-8/12 bg-white p-5 grid gap-2 rounded-md">

            <h1 className="text-3xl mb-10">
                Restaurar contrasenya
            </h1>

            <h1 className="font-bold">
                Empresa: <span className="font-normal">{formData.email}</span>
            </h1>

            <FieldComponent title="Contrasenya" name="password" type="password"
                value={formData.password} action={handleInput} necessary/>

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