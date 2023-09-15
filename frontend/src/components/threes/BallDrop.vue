<template>
  <div class="about-banner">
    <div class="canvas">
      <div id="info" ref="canvas"></div>
      <div class="text">
        <span style="color: var(--color-blue)">G</span>
        ood <span style="color: var(--color-yellow)">F</span>riends
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import * as THREE from 'three';
import { RapierPhysics, type RapierPhysicsObject } from 'three/addons/physics/RapierPhysics.js';
import { onMounted, ref } from 'vue';

const canvas = ref();

let camera: THREE.PerspectiveCamera;
let scene: THREE.Scene;
let renderer: THREE.WebGLRenderer;

let controller1: THREE.XRTargetRaySpace;
let controller2: THREE.XRTargetRaySpace;

let spheres: THREE.InstancedMesh;
let physics: RapierPhysicsObject,
  velocity = new THREE.Vector3();

let count = 0;
const colors = [0xfcc61f, 0x198bf3, 0xf4d160, 0xfcc61f];

onMounted(async () => {
  canvas.value?.appendChild(init());
  await initPhysics();
});

function init() {
  scene = new THREE.Scene();
  scene.background = new THREE.Color(0xffffff);
  camera = new THREE.PerspectiveCamera(40, window.innerWidth / 900, 0.1, 10);
  camera.position.set(0, 1, 2);

  scene.add(new THREE.HemisphereLight(0xbbbbbb, 0x888888, 3));
  const light = new THREE.DirectionalLight(0xffffff, 3);
  light.position.set(1, 1, 1).normalize();
  scene.add(light);

  renderer = new THREE.WebGLRenderer({ antialias: true });
  renderer.setPixelRatio(window.devicePixelRatio);
  renderer.setSize(window.innerWidth, 900);
  console.log(window.innerHeight);
  renderer.setAnimationLoop(render);
  renderer.xr.enabled = true;

  controller1 = renderer.xr.getController(0);

  scene.add(controller1);

  controller2 = renderer.xr.getController(1);
  scene.add(controller2);

  return renderer.domElement;
}

async function initPhysics() {
  physics = await RapierPhysics();

  {
    // Floor
    const geometry = new THREE.BoxGeometry(6, 3, 6);
    const material = new THREE.MeshNormalMaterial();

    const floor = new THREE.Mesh(geometry, material);
    floor.position.y = -1;
    physics.addMesh(floor);

    // Walls

    const wallPX = new THREE.Mesh(geometry, material);
    wallPX.position.set(4, 3, 0);
    wallPX.rotation.z = Math.PI / 2;
    physics.addMesh(wallPX);

    const wallNX = new THREE.Mesh(geometry, material);
    wallNX.position.set(-4, 3, 0);
    wallNX.rotation.z = Math.PI / 2;
    physics.addMesh(wallNX);

    const wallPZ = new THREE.Mesh(geometry, material);
    wallPZ.position.set(0, 3, 4);
    wallPZ.rotation.x = Math.PI / 2;
    physics.addMesh(wallPZ);

    const wallNZ = new THREE.Mesh(geometry, material);
    wallNZ.position.set(0, 3, -4);
    wallNZ.rotation.x = Math.PI / 2;
    physics.addMesh(wallNZ);
  }

  // Spheres

  const geometry = new THREE.IcosahedronGeometry(0.08, 3);
  const material = new THREE.MeshLambertMaterial();

  spheres = new THREE.InstancedMesh(geometry, material, 70);
  spheres.instanceMatrix.setUsage(THREE.DynamicDrawUsage); // will be updated every frame
  scene.add(spheres);

  const matrix = new THREE.Matrix4();
  const color = new THREE.Color();

  for (let i = 0; i < spheres.count; i++) {
    const x = Math.random() * 4 - 2;
    const y = Math.random() * 4;
    const z = Math.random() * 4 - 2;

    matrix.setPosition(x, y, z);
    spheres.setMatrixAt(i, matrix);
    // spheres.setColorAt(i, color.setHex(0xffffff * Math.random()));

    spheres.setColorAt(i, color.setHex(colors[Math.floor(Math.random() * colors.length)]));
  }

  physics.addMesh(spheres, 1, 1.8);
}

//

function handleController(controller: THREE.XRTargetRaySpace) {
  if (controller.userData.isSelecting) {
    physics.setMeshPosition(spheres, controller.position, count);

    velocity.x = (Math.random() - 0.5) * 2;
    velocity.y = (Math.random() - 0.5) * 2;
    velocity.z = Math.random() - 9;
    velocity.applyQuaternion(controller.quaternion);

    physics.setMeshVelocity(spheres, velocity, count);

    if (++count === spheres.count) count = 0;
  }
}

function render() {
  handleController(controller1);
  handleController(controller2);

  renderer.render(scene, camera);
}
</script>

<style scoped>
.about-banner {
  width: 100%;
  height: 100%;
  position: relative;
}
#info {
  overflow: hidden;
  position: absolute;
  height: 900px;

  width: 100%;
  text-align: center;
  display: block;
}
.canvas {
  width: 100%;
  height: 900px;
}
.text {
  width: 100%;
  height: 100%;
  display: flex;
  padding-top: 200px;
  justify-content: center;
  backdrop-filter: blur(7px);
  font-family: 'LINESeedKR-Bd';
  position: absolute;
  color: #525252;
  font-size: 130px;
}
</style>
