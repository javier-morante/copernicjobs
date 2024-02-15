import FieldComponent from "../../general_form_components/FieldComponent";
import SaveCancelComponent from "../../general_form_components/SaveCancelComponent";
import MultilineFieldComponent from "../../general_form_components/MultilineFieldComponent";
import FileInputWithImageComponent from "../../general_form_components/FileInputWithImageComponent";


export default function CompanyUserProfileForm(
    {formData, handleInput, handleSubmit,handleFileChange,imageRef}
) {
    
    return (
        <form onSubmit={handleSubmit} className="py-5">
            <h1 className="text-2xl font-bold">
                Perfil
            </h1>

            <br/>

          <br/>

            {/* Name and Surname Fields */}

            <div className="w-full flex gap-5">
              <FieldComponent title="Nom" name="name" 
                  action={handleInput} value={formData.name} 
                  necessary/>
              
              <div className="w-fit flex place-items-right justify-end">
                <FileInputWithImageComponent 
                    onChange={handleFileChange}
                    reference={imageRef}
                    img={formData.iconPath}/>  
              </div>
            </div>


            <div className="grid">
              <FieldComponent title="Telèfon" name="phone"
                  action={handleInput} value={formData.phone} 
                  necessary/>
            </div>
          
            <br/>

            {/* Email field */}
            <div className="grid">

                <FieldComponent title="NIF" name="nif"
                        action={handleInput} value={formData.nif}/>


                <br/>

                <FieldComponent title="Correu electrònic" name="email"
                    action={handleInput} value={formData.email}/>
                <br/>

            </div>


            {/* Description Field */}
            <div>
                <MultilineFieldComponent title="Descripció"
                    name="description" maxSize={200} 
                    value={formData.description} action={handleInput}/>
            </div>

            <br/>

            {/* WebPage URL Field */}
            <div>
                <FieldComponent title="Pàgina web" name="webPageUrl"
                        action={handleInput} value={formData.webPageUrl}/>
            </div>

            <br/><br/>

            <SaveCancelComponent/>
            
        </form>
    )
}