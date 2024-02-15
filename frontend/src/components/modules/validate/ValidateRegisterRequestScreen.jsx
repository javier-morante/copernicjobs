import { useEffect, useState } from "react"
import { getAccesRequest } from '../../../axios/acces_request/AccesRequest'
import ValidateRequest from "./ValidationRequest";

function ValidateRegisterRequestScreen() {
  const [registerRequest, setRegisterRequest] = useState([]);

  const fetchData = async () => {
    const data = await getAccesRequest();
    if (Array.isArray(data)) {
      setRegisterRequest(data);
    } else {
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleRelaod= (index)=>{
    const arr = [...registerRequest]
    arr.splice(index,1)
    setRegisterRequest(arr);
  }

  return (

    <div className="flex flex-col gap-4 m-4">
      {registerRequest.length ?
        registerRequest.map((element,index) => {
          return <ValidateRequest key={element.nif} request={element} reload={handleRelaod} index={index}/>;
        })
        : <h1 className="place-self-center text-2xl">No hi ha sol·licituds</h1>
      }
    </div>

  )

}

export default ValidateRegisterRequestScreen;