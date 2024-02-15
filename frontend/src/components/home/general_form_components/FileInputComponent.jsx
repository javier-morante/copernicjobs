

import { useState } from 'react';

const FileInputComponent = ({ onChange, accept, reference, value}) => {
    const [isDraging, setIsDraging] = useState(false)

    const handleDragOver = (e) => {
        e.preventDefault();
    };

    const handleDragEnter = () => {
        setIsDraging(true);
    };

    const handleDragLeave = () => {
        setIsDraging(false);
    };

    const handleDrop = (e) => {
        e.preventDefault();
        reference.current.files = e.dataTransfer.files;
        setIsDraging(false)
        onChange(null,reference)
    };

    const handleClick = () => {
        reference.current.click()
    }

    return (
        <label htmlFor="images" className={`drop-container hover:bg-slate-400 ${isDraging && "bg-slate-400"}`} onClick={handleClick}  onDragOver={handleDragOver} onDragEnter={handleDragEnter} onDragLeave={handleDragLeave} onDrop={handleDrop}>
            {isDraging ? (
                <div className='drop-title'>
                    Drop to save
                </div>
            ) : (
                <div className='text-center'>
                    <p className="drop-title">Arrosega el fitxer aquí</p>
                    <p>o</p>
                    <p className="drop-title">Selecciona un arxiu</p>
                    <p className='p-2'>{reference.current && reference.current.files[0] ?reference.current.files[0].name:''}</p>
                </div>
            )

            }
            <input type="file" onChange={onChange} accept={accept} ref={reference} className='hidden'/>
        </label>
    );
};

export default FileInputComponent;
