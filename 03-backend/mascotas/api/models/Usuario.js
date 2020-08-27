/**
 * Usuario.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {
  tableName:'epn_usuario',
  attributes: {
    //Se puede definir el nombre de la tabla
      cedula:{
        type: 'string',
        required: true,
        columnName: 'epn_cedula',
        unique: true,
        minLength: 10,
        maxLength: 25
      },
      nombre:{
        type: 'string',
        minLength: 3,
        required : true
      },
      correo:{
        type: 'string',
        isEmail: true
      },
      estadoCivil:{
        type:'string',
        isIn:['Soltero', 'Casado', 'Divorciado', 'Viudo', 'Unión libre'],
        defaultsTo:'Soltero'
      },
      password:{
        type: 'string',
        regex: /^[a-zA-Z]\w{3,14}$/
      },
      pokemons:{//One to many
        collection: 'pokemon', //referencia al nombre de nuestro modelo
        via: 'usuario' //atributo que se va a buscar dentro del papa para relacionar
      }
  },
  
};

