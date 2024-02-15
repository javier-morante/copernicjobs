

import { useEffect, useState } from 'react';

const FileInputWithImageComponent = ({ onChange, accept, reference, img}) => {
    const [isDraging, setIsDraging] = useState(false)
    const [isImageBackGround, setIsImageBackGround] = useState(false)

    const isGoodImage = (element)=> {return element?element.length === 0:true};

    useEffect(()=>{
        
        setIsImageBackGround(isGoodImage(img))},[img])

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
        <label htmlFor="images" className={`w-24 h-24 rounded-full bg-cover bg-center border-dashed border-2 border-gray-500 flex items-center justify-center cursor-pointer`} style={ {backgroundImage: `url(${(img)})`} } onClick={handleClick}  onDragOver={handleDragOver} onDragEnter={handleDragEnter} onDragLeave={handleDragLeave} onDrop={handleDrop}>
            {isDraging ? (
                <div className='drop-title'>
                    Drop to save
                </div>
            ) : (
                isImageBackGround  && 
                <div className='text-center'>
                    <p className="drop-title text-xs">Drop files here</p>
                    <p>o</p>
                    <p className="drop-title text-xs">Selecciona un arxiu</p>
                </div>
            )

            }
            <input type="file" onChange={onChange} accept={accept} ref={reference} className='hidden'/>
        </label>
    );
};

export default FileInputWithImageComponent;
