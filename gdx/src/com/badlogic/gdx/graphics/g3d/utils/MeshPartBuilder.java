package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.materials.Material;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool.Poolable;

public interface MeshPartBuilder {
	/** @return The {@link MeshPart} currently building. */
	public MeshPart getMeshPart();
	/** @return The {@link VertexAttributes} available for building. */
	public VertexAttributes getAttributes();
	/** Set the color used if no vertex color is provided, or null to not use a default color. */
	public void setColor(final Color color);
	/** Set the color used if no vertex color is provided. */
	public void setColor(float r, float g, float b, float a);
	/** Add one or more vertices, returns the index of the last vertex added. The length of values must a power of the vertex size. */
	public short vertex(final float[] values);
	/** Add a vertex, returns the index. Null values are allowed. Use {@link #getAttributes} to check which values are available. */
	public short vertex(Vector3 pos, Vector3 nor, Color col, Vector2 uv);
	/** Add a vertex, returns the index. Use {@link #getAttributes} to check which values are available. */
	public short vertex(final VertexInfo info);
	/** Add an index, MeshPartBuilder expects all meshes to be indexed. */
	public void index(final short value);
	/** Add multiple indices, MeshPartBuilder expects all meshes to be indexed. */
	public void index(short value1, short value2);
	/** Add multiple indices, MeshPartBuilder expects all meshes to be indexed. */
	public void index(short value1, short value2, short value3);
	/** Add multiple indices, MeshPartBuilder expects all meshes to be indexed. */
	public void index(short value1, short value2, short value3, short value4);
	/** Add multiple indices, MeshPartBuilder expects all meshes to be indexed. */
	public void index(short value1, short value2, short value3, short value4, short value5, short value6);
	/** Add multiple indices, MeshPartBuilder expects all meshes to be indexed. */
	public void index(short value1, short value2, short value3, short value4, short value5, short value6, short value7, short value8);
	/** Add a line by indices. */
	public void line(short index1, short index2);
	/** Add a line. */
	public void line(VertexInfo p1, VertexInfo p2);
	/** Add a line. */
	public void line(Vector3 p1, Vector3 p2);
	/** Add a line. */
	public void line(float x1, float y1, float z1, float x2, float y2, float z2);
	/** Add a line. */
	public void line(Vector3 p1, Color c1, Vector3 p2, Color c2);
	/** Add a triangle by indices. */
	public void triangle(short index1, short index2, short index3);
	/** Add a triangle. */
	public void triangle(VertexInfo p1, VertexInfo p2, VertexInfo p3);
	/** Add a triangle. */
	public void triangle(Vector3 p1, Vector3 p2, Vector3 p3);
	/** Add a triangle. */
	public void triangle(Vector3 p1, Color c1, Vector3 p2, Color c2, Vector3 p3, Color c3);
	/** Add a rectangle by indices. */
	public void rect(short corner00, short corner10, short corner11, short corner01);
	/** Add a rectangle. */
	public void rect(VertexInfo corner00, VertexInfo corner10, VertexInfo corner11, VertexInfo corner01);
	/** Add a rectangle. */
	public void rect(Vector3 corner00, Vector3 corner10, Vector3 corner11, Vector3 corner01, Vector3 normal);
	/** Add a rectangle */
	public void rect(float x00, float y00, float z00, float x10, float y10, float z10, float x11, float y11, float z11, float x01, float y01, float z01, float normalX, float normalY, float normalZ);
	/** Add a box */
	public void box(VertexInfo corner000, VertexInfo corner010, VertexInfo corner100, VertexInfo corner110,
						VertexInfo corner001, VertexInfo corner011, VertexInfo corner101, VertexInfo corner111);
	/** Add a box */
	public void box(Vector3 corner000, Vector3 corner010, Vector3 corner100, Vector3 corner110,
						Vector3 corner001, Vector3 corner011, Vector3 corner101, Vector3 corner111);
	/** Add a box given the matrix */
	public void box(Matrix4 transform);
	/** Add a box with the specified dimensions */
	public void box(float width, float height, float depth);
	/** Add a box at the specified location, with the specified dimensions */
	public void box(float x, float y, float z, float width, float height, float depth);
	/** Add a cylinder */
	public void cylinder(float width, float height, float depth, int divisions);
	/** Add a cone */
	public void cone(float width, float height, float depth, int divisions);
	/** Add a sphere */
	public void sphere(float width, float height, float depth, int divisionsU, int divisionsV);
	// FIXME: Add capsule
	
	/** Class that contains all vertex information the builder can use.
	 * @author Xoppa */
	public static class VertexInfo implements Poolable {
		public final Vector3 position = new Vector3();
		public boolean hasPosition;
		public final Vector3 normal = new Vector3(0, 1, 0);
		public boolean hasNormal;
		public final Color color = new Color(1, 1, 1, 1);
		public boolean hasColor;
		public final Vector2 uv = new Vector2();
		public boolean hasUV;
		@Override
		public void reset () {
			position.set(0,0,0);
			normal.set(0,1,0);
			color.set(1,1,1,1);
			uv.set(0,0);
		}
		public VertexInfo set(Vector3 pos, Vector3 nor, Color col, Vector2 uv) {
			reset();
			if ((hasPosition = pos != null) == true)
				position.set(pos);
			if ((hasNormal = nor != null) == true)
				normal.set(nor);
			if ((hasColor = col != null) == true)
				color.set(col);
			if ((hasUV = uv != null) == true)
				this.uv.set(uv);
			return this;
		}
		public VertexInfo setPos(float x, float y, float z) {
			position.set(x,y,z);
			hasPosition = true;
			return this;
		}
		public VertexInfo setPos(Vector3 pos) {
			if ((hasPosition = pos != null)==true)
				position.set(pos);
			return this;
		}
		public VertexInfo setNor(float x, float y, float z) {
			normal.set(x,y,z);
			hasNormal = true;
			return this;
		}
		public VertexInfo setNor(Vector3 nor) {
			if ((hasNormal = nor != null) == true)
				normal.set(nor);
			return this;
		}
		public VertexInfo setCol(float r, float g, float b, float a) {
			color.set(r,g,b,a);
			hasColor = true;
			return this;
		}
		public VertexInfo setCol(Color col) {
			if ((hasColor = col != null)==true)
				color.set(col);
			return this;
		}
		public VertexInfo setUV(float u, float v) {
			uv.set(u,v);
			hasUV = true;
			return this;
		}
		public VertexInfo setUV(Vector2 uv) {
			if ((hasUV = uv != null)==true)
				this.uv.set(uv);
			return this;
		}
	}
}