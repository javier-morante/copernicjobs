import React from 'react';
import '../../../index.css';

const Confetti = (
    {children}
) => {
  // Función para generar un color aleatorio en formato hexadecimal
  const getRandomColor = () => {
    return '#' + Math.floor(Math.random()*16777215).toString(16);
  };

  // Función para generar las partículas de confeti
  const generateConfetti = () => {
    const confettiCount = 50; // Número de partículas de confeti
    const confettiElements = [];

    for (let i = 0; i < confettiCount; i++) {
      const randomLeft = Math.random() * 100; // Posición horizontal aleatoria
      const randomDelay = Math.random() * 5; // Retraso de animación aleatorio
      const randomColor = getRandomColor(); // Color aleatorio

      const confettiElement = (
        <div
          key={i}
          className="confetti"
          style={{
            left: `${randomLeft}%`,
            animationDelay: `${randomDelay}s`,
            backgroundColor: randomColor
          }}
        >
        </div>
      );

      confettiElements.push(confettiElement);
    }

    return confettiElements;
  };

  return (
    <div className='fixed top-0 left-0 w-full h-full'>
      {generateConfetti()}
      {children}
    </div>
  );
};

export default Confetti;